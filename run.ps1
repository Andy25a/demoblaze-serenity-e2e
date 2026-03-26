[CmdletBinding()]
param(
    [switch]$Headless,
    [switch]$SkipOpenReport,
    [switch]$SkipEvidence,
    [string]$Browser = "chrome"
)
# Por defecto se abre reports\serenity\index.html al terminar. Use -SkipOpenReport para no abrir el navegador.

$ErrorActionPreference = "Stop"
$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$logDir = Join-Path $projectRoot "reports\execution-logs"
New-Item -ItemType Directory -Force -Path $logDir | Out-Null
$timestamp = Get-Date -Format "yyyyMMdd-HHmmss"
$logFile = Join-Path $logDir "run-$timestamp.log"

function Write-Section {
    param([string]$Message)
    Write-Host ""
    Write-Host "==== $Message ====" -ForegroundColor Cyan
}

Write-Section "Validaciones previas"
if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
    throw "No se encontró Java en PATH."
}
if (-not (Test-Path (Join-Path $projectRoot "mvnw.cmd"))) {
    throw "No se encontró mvnw.cmd en la raíz del proyecto."
}

Write-Section "Parámetros de ejecución"
Write-Host "Browser : $Browser"
Write-Host "Headless: $Headless"
Write-Host "Log     : $logFile"

$mavenArgs = @(
    "clean",
    "verify",
    "-Dwebdriver.driver=$Browser"
)

if ($Headless) {
    $mavenArgs += "-Dchrome.switches=--headless=new;--window-size=1920,1080;--disable-gpu"
}

Write-Section "Ejecución de pruebas E2E"
Write-Host "Comando: .\mvnw.cmd $($mavenArgs -join ' ')"

$argLine = $mavenArgs -join " "
cmd /c "cd /d `"$projectRoot`" && mvnw.cmd $argLine 2>&1" | Tee-Object -FilePath $logFile
if ($LASTEXITCODE -ne 0) {
    throw "La ejecución de pruebas falló. Revisa el log: $logFile"
}

Write-Section "Exportación de reporte a reports\serenity"
$source = Join-Path $projectRoot "target\site\serenity"
$dest = Join-Path $projectRoot "reports\serenity"
if (-not (Test-Path $source)) {
    throw "No existe '$source'. El build no generó el reporte Serenity."
}
if (Test-Path $dest) {
    Remove-Item -Recurse -Force -Path $dest
}
New-Item -ItemType Directory -Force -Path $dest | Out-Null
Write-Host "Copiando reporte Serenity..." -ForegroundColor Cyan
Copy-Item -Recurse -Force -Path (Join-Path $source "*") -Destination $dest
Write-Host "OK: $dest\index.html" -ForegroundColor Green

if (-not $SkipEvidence) {
    Write-Section "Paquete de evidencia (carpeta + ZIP)"
    $evidenceTs = Get-Date -Format "yyyyMMdd-HHmmss"
    $evidenceRoot = Join-Path $projectRoot "reports\evidence\$evidenceTs"
    New-Item -ItemType Directory -Force -Path $evidenceRoot | Out-Null

    Write-Host "Copiando artefactos de evidencia..." -ForegroundColor Cyan
    $pathsToCopy = @(
        "reports\serenity",
        "target\surefire-reports",
        "reports\execution-logs"
    )
    foreach ($relativePath in $pathsToCopy) {
        $srcPath = Join-Path $projectRoot $relativePath
        if (Test-Path $srcPath) {
            $destPath = Join-Path $evidenceRoot $relativePath
            New-Item -ItemType Directory -Force -Path (Split-Path $destPath -Parent) | Out-Null
            Copy-Item -Recurse -Force -Path $srcPath -Destination $destPath
        }
    }

    $metaFile = Join-Path $evidenceRoot "execution-metadata.txt"
    $javaVersion = (cmd /c "java -version 2>&1" | Select-Object -First 1)
    $meta = @(
        "Execution Timestamp: $evidenceTs",
        "Machine           : $env:COMPUTERNAME",
        "User              : $env:USERNAME",
        "Java Version      : $javaVersion",
        "Project           : demoblaze-serenity-e2e",
        "Scenario          : Flujo E2E de compra en Demoblaze"
    )
    $meta | Set-Content -Path $metaFile -Encoding UTF8

    $zipPath = Join-Path $projectRoot "reports\evidence-$evidenceTs.zip"
    if (Test-Path $zipPath) {
        Remove-Item -Force $zipPath
    }
    Compress-Archive -Path (Join-Path $evidenceRoot "*") -DestinationPath $zipPath

    Write-Host "Evidencia (carpeta): $evidenceRoot" -ForegroundColor Green
    Write-Host "Evidencia (ZIP)     : $zipPath" -ForegroundColor Green
}

$baseReport = Join-Path $projectRoot "target\site\serenity\index.html"
$versionedReport = Join-Path $projectRoot "reports\serenity\index.html"

Write-Section "Resultado"
Write-Host "Ejecución completada correctamente." -ForegroundColor Green
Write-Host "Reporte base      : $baseReport"
Write-Host "Reporte versionado: $versionedReport"
Write-Host "Log de ejecución  : $logFile"

if (-not $SkipOpenReport -and (Test-Path $versionedReport)) {
    Write-Section "Abriendo reporte HTML"
    Start-Process $versionedReport
}
