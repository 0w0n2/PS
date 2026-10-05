import java.util.*;

// 장르 별로 가장 많이 재생된 노래를 두 개씩(최소 1개) 모음
// -> 장르 별로 가장 많이 재생된 노래 2개까지만 기록
class Solution {

    static class Play {
        int id;
        int play;

        Play(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    static class Genre implements Comparable<Genre> {
        String name;
        int playTotal;

        Play[] top = new Play[2]; // 상위 2개

        Genre(String name) {
            this.name = name;
        }

        void add(Play p) {
            playTotal += p.play;

            if (top[0] == null || isBetter(p, top[0])) {
                top[1] = top[0];
                top[0] = p;
            } else if (top[1] == null || isBetter(p, top[1])) {
                top[1] = p;
            }
        }

        // 재생수 내림차순, 같으면 id 오름차순
        private boolean isBetter(Play a, Play b) {
            return (a.play != b.play) ? a.play > b.play : a.id < b.id;
        }

        @Override
        public int compareTo(Genre o) {
            return Integer.compare(o.playTotal, this.playTotal);
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Genre> genreInfos = new HashMap<>();

        for (int i=0; i<genres.length; i++) {
            Genre genre = genreInfos.computeIfAbsent(genres[i], Genre::new);
            genre.add(new Play(i, plays[i]));
        }

        Genre[] genreArr = genreInfos.values().toArray(new Genre[0]);
        Arrays.sort(genreArr);

        int size = 0;
        for (Genre g : genreArr) {
            size += g.top[1] == null ? 1 : 2;
        }

        int[] answer = new int[size];
        int idx = 0;

        for (Genre g : genreArr) {
            answer[idx++] = g.top[0].id;

            if (g.top[1] != null) {
                answer[idx++] = g.top[1].id;
            }
        }

        return answer;
    }
}