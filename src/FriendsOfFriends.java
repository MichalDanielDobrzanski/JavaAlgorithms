import input.AdjacencyListData;

import java.util.*;

/**
 * Find friend of friends suggestions for a user.
 */
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


        System.out.println("Friends of size two for " + user + " are: " + findFriendsOfSizeTwo(user, adjacencyList));
        System.out.println("Friends of size two for " + otherUser + " are: " + findFriendsOfSizeTwo(otherUser, adjacencyList));
    }


    // 0 : (2,3)
    // 1 : (2)
    // 2 : (0,1)
    // 3 : (0,4)
    // 4 : (3,5)
    // 5 : (4)
    // expected:
    // Friends for 0 are: [1, 4, 5]
    // Friends for 2 are: [3, 4, 5]
    private Set<Integer> findFriends(int user, List<Integer>[] adjacencyList) {
        Set<Integer> friendsOfFriends = new HashSet<>();

        boolean[] visited = new boolean[adjacencyList.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(user);
        visited[user] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < adjacencyList[current].size(); i++) {
                int friend = adjacencyList[current].get(i);
                if (!visited[friend]) {
                    queue.add(friend);
                    visited[friend] = true;

                    for (int j = 0; j < adjacencyList[friend].size(); j++) {
                        int friendOfFriend = adjacencyList[friend].get(j);
                        if (friendOfFriend != user && !adjacencyList[user].contains(friendOfFriend)) {
                            friendsOfFriends.add(friendOfFriend);
                        }
                    }
                }
            }
        }
        return friendsOfFriends;
    }

    private Set<Integer> findFriendsOfSizeTwo(int user, List<Integer>[] adjacencyList) {
        Set<Integer> friendsOfFriends = new HashSet<>();

        boolean[] visited = new boolean[adjacencyList.length];

        Queue<UserDepth> queue = new LinkedList<>();
        queue.add(new UserDepth(user, 0));
        visited[user] = true;

        while (!queue.isEmpty()) {
            UserDepth userDepth = queue.poll();
            int current = userDepth.user;

            for (int i = 0; i < adjacencyList[current].size(); i++) {
                int friend = adjacencyList[current].get(i);
                if (visited[friend]) continue;
                visited[friend] = true;
                queue.add(new UserDepth(friend, userDepth.depth + 1));

                if (userDepth.depth == 1) {
                    friendsOfFriends.add(friend);
                }
            }
        }
        return friendsOfFriends;
    }


    private record UserDepth(int user, int depth) {
    }

    @Override
    protected String describe() {
        return "Finding friends of friends algorithm - John, Paul, Tom, George, Rob, Cory";
    }
}
