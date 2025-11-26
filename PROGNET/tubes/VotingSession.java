import java.util.LinkedHashMap;
import java.util.Map;

public class VotingSession {

    private String title;
    private boolean isActive;
    private Map<String, Integer> voteData;

    public VotingSession(String title, String[] candidates) {
        this.title = title;
        this.isActive = true;
        this.voteData = new LinkedHashMap<>();
        
        for (String name : candidates) {
            if (name != null && !name.trim().isEmpty()) {
                voteData.put(name.trim(), 0);
            }
        }
    }

    public synchronized boolean addVote(String candidateName) {
        if (!isActive) {
            return false;
        }
        if (voteData.containsKey(candidateName)) {
            voteData.put(candidateName, voteData.get(candidateName) + 1);
            return true;
        }
        return false;
    }

    public void endSession() {
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getTitle() {
        return title;
    }

    public String getCandidatesStr() {
        return String.join(",", voteData.keySet());
    }

    public Map<String, Integer> getResults() {
        return new LinkedHashMap<>(voteData);
    }
}