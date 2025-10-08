public class LinkedList {
    Node LegendsHead;
    Node LegendsTail;
    int idxLegends = 0;

    public void addLegendsAwal(String playerName, String position, String specialAbility, String skillPower,
            String skillSpeed, String skillStamina, float overallRating) {
        Node newLegends = new Node(playerName, position, specialAbility, skillPower, skillSpeed, skillStamina,
                overallRating);

        if (LegendsHead == null) {
            LegendsHead = newLegends;
            LegendsTail = newLegends;
            idxLegends++;
        } else {

        }

    }
}
