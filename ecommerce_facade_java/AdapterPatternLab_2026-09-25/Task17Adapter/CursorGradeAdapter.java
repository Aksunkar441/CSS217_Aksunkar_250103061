package Task17Adapter;

public class CursorGradeAdapter implements IGradeProvider {
    private final LegacyDataCursor cursor;

    public CursorGradeAdapter(LegacyDataCursor cursor) { this.cursor = cursor; }
    public CursorGradeAdapter() { this(new LegacyDataCursor()); }
    @Override public GradeRecord fetchCurrentGrade() {
        return new GradeRecord(cursor.getString("COURSE_NAME"), cursor.getInt("STUDENT_SCORE"));
    }
}
