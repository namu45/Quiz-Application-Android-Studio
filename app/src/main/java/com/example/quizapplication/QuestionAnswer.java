package com.example.quizapplication;

public class QuestionAnswer {

    public static String question[] = {
            "Which company developed Android Studio?",
            "What language is Android Studio primarily used for developing Android apps?",
            "Which layout is best for creating a responsive UI in Android?",
            "What is the default file extension for Android Studio project files?",
            "Which tool helps in debugging Android apps?",
            "Which class is used to launch a new Android activity?",
            "What is the minimum SDK version required to run Android Studio?"
    };

    public static String choices[][] = {
            {"Google", "Apple", "Microsoft", "Samsung"},
            {"Java", "Kotlin", "Python", "C++"},
            {"LinearLayout", "RelativeLayout", "ConstraintLayout", "FrameLayout"},
            {".apk", ".java", ".xml", ".gradle"},
            {"Android Debug Bridge (ADB)", "Logcat", "Emulator", "Profiler"},
            {"Intent", "Broadcast", "Service", "Activity"},
            {"API 16", "API 21", "API 25", "API 30"}
    };

    public static String correctAnswers[] = {
            "Google",
            "Kotlin",
            "ConstraintLayout",
            ".xml",
            "Logcat",
            "Intent",
            "API 21"
    };
}
