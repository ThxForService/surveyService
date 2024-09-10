package xyz.sangdam.psychologicalTest.enums;

public enum PsychologicalTestType {
    COMPULSION("compulsion"),
    EVASION("evasion"),
    STRESS("stress"),
    INTERNET_ADDICTION("internet-addiction"),
    EATING_DISORDER("eating-disorder");

    private final String path;

    PsychologicalTestType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}