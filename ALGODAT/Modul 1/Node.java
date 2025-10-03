public class Node {
    String playerName;
    String position;
    String specialAbility;
    String skillPower;
    String skillSpeed;
    String skillStamina;
    float overallRating;

    Node next;
    Node prev;

    public Node(String playerName, String position, String specialAbility, String skillPower, String skillSpeed,
            String skillStamina, float overallRating) {
        this.playerName = playerName;
        this.position = position;
        this.specialAbility = specialAbility;
        this.skillPower = skillPower;
        this.skillSpeed = skillSpeed;
        this.skillStamina = skillStamina;
        this.overallRating = overallRating;
        this.next = null;
        this.prev = null;
    }

}
