class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 모든 시간을 초 단위로 변환
        int videoLenSeconds = convertToSeconds(video_len);
        int posSeconds = convertToSeconds(pos);
        int opStartSeconds = convertToSeconds(op_start);
        int opEndSeconds = convertToSeconds(op_end);
        
        // 처음부터 오프닝 구간에 있는지 확인
        if (posSeconds >= opStartSeconds && posSeconds <= opEndSeconds) {
            posSeconds = opEndSeconds;
        }
        
        // 명령 실행
        for (String command : commands) {
            if (command.equals("prev")) {
                // 10초 전으로 이동
                posSeconds -= 10;
                if (posSeconds < 0) {
                    posSeconds = 0;
                }
            } else if (command.equals("next")) {
                // 10초 후로 이동
                posSeconds += 10;
                if (posSeconds > videoLenSeconds) {
                    posSeconds = videoLenSeconds;
                }
            }
            
            // 이동 후 오프닝 구간에 있는지 확인
            if (posSeconds >= opStartSeconds && posSeconds <= opEndSeconds) {
                posSeconds = opEndSeconds;
            }
        }
        
        // 초를 다시 "mm:ss" 형식으로 변환
        return convertToTimeFormat(posSeconds);
    }
    
    // 시간 문자열을 초로 변환하는 함수
    private int convertToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }
    
    // 초를 "mm:ss" 형식으로 변환하는 함수
    private String convertToTimeFormat(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
