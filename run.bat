@echo off
if not exist bin mkdir bin
javac -d bin --module-path "C:/Users/DELL/Downloads/openjfx-17.0.17_windows-x64_bin-sdk/javafx-sdk-17.0.17/lib" --add-modules javafx.controls,javafx.fxml,javafx.web -sourcepath src src/MatiMate.java src/models/*.java src/utils/*.java src/views/*.java src/views/cuestionarios/*.java src/views/limites/*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
java --module-path "C:/Users/DELL/Downloads/openjfx-17.0.17_windows-x64_bin-sdk/javafx-sdk-17.0.17/lib" --add-modules javafx.controls,javafx.fxml,javafx.web -cp bin MatiMate
