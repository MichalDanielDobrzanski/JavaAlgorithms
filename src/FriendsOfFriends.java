import input.AdjacencyListData;

import java.util.*;

public class FriendsOfFriends extends BaseAlgorithm<AdjacencyListData> {

    FriendsOfFriends(AdjacencyListData adjacencyListData) {
        super(adjacencyListData);
    }

    @Override
    void execute() {
        List<Integer>[] adjacencyList = input.adjacencyList;

        int user = 0;
        Set<Integer> friendsOfFriends = findFriends(user, adjacencyList);
        System.out.println("Friends for " + user + " are: " + friendsOfFriends);
        int otherUser = 2;
        System.out.println("Friends for " + otherUser + " are: " + findFriends(otherUser, adjacencyList));
    }

    // 0 : (2,3)
    // 1 : (2)
    // 2 : (0,1)
    // 3 : (0,4)
    // 4 : (3,5)
    // 5 : (4)
    private Set<Integer> findFriends(int user, List<Integer>[] adjacencyList) {
        Set<Integer> friendsOfFriends = new HashSet<>();

        boolean[] visited = new boolean[adjacencyList.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(user);
        visited[user] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int friend: adjacencyList[current]) {
                if (visited[friend]) continue;
                visited[friend] = true;
                queue.add(friend);

                // friends check
                for (int friendOfFriend: adjacencyList[friend]) {
                    if (friendOfFriend != user && !adjacencyList[user].contains(friendOfFriend)) {
                        friendsOfFriends.add(friendOfFriend);
                    }
                }
            }
        }

        return friendsOfFriends;
    }


    @Override
    protected String describe() {
        return "Finding friends of friends algorithm - John, Paul, Tom, George, Rob, Cory";
    }
}
