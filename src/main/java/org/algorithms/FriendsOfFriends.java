import input.AdjacencyListData;
import input.base.BaseAlgorithm;

import java.util.*;

/**
 * Find friend of friends suggestions for a user.
 */
public class FriendsOfFriends extends BaseAlgorithm<AdjacencyListData> {

    public FriendsOfFriends(AdjacencyListData adjacencyListData) {
        super(adjacencyListData);
    }

    @Override
    public void execute() {
        List<Integer>[] adjacencyList = input.data;

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

        Queue<UserDepth> queue = new LinkedList<>();
        queue.add(new UserDepth(user, 0));
        visited[user] = true;

        while (!queue.isEmpty()) {
            UserDepth curr = queue.poll();
            if (curr.depth >= 2) {
                friendsOfFriends.add(curr.user);
            }

            for (int friend : adjacencyList[curr.user]) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    queue.add(new UserDepth(friend, curr.depth + 1));
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
            if (userDepth.depth == 2) {
                friendsOfFriends.add(userDepth.user);
            }

            int current = userDepth.user;
            for (int i = 0; i < adjacencyList[current].size(); i++) {
                int friend = adjacencyList[current].get(i);
                if (!visited[friend]) {
                    visited[friend] = true;
                    queue.add(new UserDepth(friend, userDepth.depth + 1));
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
