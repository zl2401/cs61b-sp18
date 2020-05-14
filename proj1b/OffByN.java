public class OffByN implements CharacterComparator {
    int gap;

    public OffByN(int N) {
        gap = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if ((diff == gap) || (diff == - gap)) {
            return true;
        }
        else {
            return false;
        }
    }
}
