@cls
@chcp 1251 >nul

@echo off
echo package javase01.t01.logic;>> "Logic.java"
echo.>>"Logic.java"
echo public class Logic {>> "Logic.java"
echo 	public String method(){>> "Logic.java"
echo 		return "I am string in Logic.";>> "Logic.java"
echo 	}>>"Logic.java"
echo.>>"Logic.java"
echo }>>"Logic.java"
if not exist src mkdir src
move Logic.java src

echo package javase01.t01.main;>> "Main.java"
echo.>>"Main.java"
echo import javase01.t01.logic.Logic;>>"Main.java"
echo.>>"Main.java"
echo public class Main {>>"Main.java"
echo.>>"Main.java"
echo	public static void main(String[] args) {>>"Main.java"
echo		Logic logic = new Logic();>>"Main.java"
echo		System.out.println(logic.method());>>"Main.java"
echo 	}>>"Main.java"
echo }>>"Main.java"
move Main.java src


@echo on
javac -d . src\Logic.java
javac -d . src\Main.java
java -cp . javase01.t01.main.Main
@pause
exit