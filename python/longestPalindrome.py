class Solution:
    # rec with memoisation - Time: O(n^2) - all substrings, Space: O(n^2)
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        if n == 0:
            return ""

        # map of tuple(int,int) -> bool
        cache = {}
        best_l = 0
        best_r = 0

        def is_palindrome(l, r):
            # Base cases
            if l >= r:  # empty or single char is palindrome
                return True
            key = (l, r)
            if key in cache:
                return cache[key]

            # Recur only if end chars match
            if s[l] == s[r] and is_palindrome(l + 1, r - 1):
                cache[key] = True
            else:
                cache[key] = False
            return cache[key]

        for l in range(n):
            for r in range(l, n):
                if is_palindrome(l, r) and (r - l) > (best_r - best_l):
                    best_l, best_r = l, r

        return s[best_l: best_r + 1]


def is_palindrome(t: str) -> bool:
    return t == t[::-1]


def brute_force_longest_pal(s: str) -> str:
    # Reference solution for validation (O(n^3) worst-case).
    best = ""
    n = len(s)

    for i in range(n):
        for j in range(i, n):
            cand = s[i:j + 1]
            if len(cand) > len(best) and is_palindrome(cand):
                best = cand
    return best


def check(s: str, got: str):
    # Basic invariants
    assert is_palindrome(got), f"Result is not a palindrome: {got!r}"
    assert got in s, f"Result is not a substring: {got!r} of {s!r}"
    # Correctness against brute force (allows any longest tie)
    expected = brute_force_longest_pal(s)
    assert len(got) == len(expected), (
        f"Wrong length for {s!r}: got {len(got)} ({got!r}) "
        f"expected {len(expected)} ({expected!r})"
    )


if __name__ == "__main__":
    sol = Solution()

    # Edge cases
    check("", sol.longestPalindrome(""))
    check("a", sol.longestPalindrome("a"))
    check("aa", sol.longestPalindrome("aa"))
    check("ab", sol.longestPalindrome("ab"))

    # Typical cases
    check("babad", sol.longestPalindrome("babad"))  # "bab" or "aba"
    check("cbbd", sol.longestPalindrome("cbbd"))  # "bb"
    check("racecar", sol.longestPalindrome("racecar"))
    check("forgeeksskeegfor", sol.longestPalindrome("forgeeksskeegfor"))  # "geeksskeeg"
    check("abacdfgdcaba", sol.longestPalindrome("abacdfgdcaba"))  # "aba"/"aca"
    check("aaaa", sol.longestPalindrome("aaaa"))  # whole string

    # Repeated patterns / overlaps
    check("abcddcbae", sol.longestPalindrome("abcddcbae"))  # "abcddcba"
    check("abaxyzzyxf", sol.longestPalindrome("abaxyzzyxf"))  # "xyzzyx"
    check("abacdfgdcabba", sol.longestPalindrome("abacdfgdcabba"))  # "abba"

    # Mixed chars & case
    check("Aa", sol.longestPalindrome("Aa"))  # case-sensitive: "A" or "a"
    check("MadamImAdam", sol.longestPalindrome("MadamImAdam"))  # likely single letters

    # Unicode (optional)
    check("åbßbå", sol.longestPalindrome("åbßbå"))  # pal with unicode chars

    # Randomized tests against brute force
    import random
    import string

    rng = random.Random(0)
    alphabet = string.ascii_lowercase
    for _ in range(100):
        size = rng.randint(0, 20)
        s = "".join(rng.choice(alphabet) for _ in range(size))
        check(s, sol.longestPalindrome(s))

    print("All tests passed ✅")
