def generateCombinations(allValues: list[int], index: int, candidate: list[int], combinations: list[list[int]]):
    if len(candidate) > 0:
        combinations.append(candidate.copy())

    for i in range(index, len(allValues)):
        candidate.append(allValues[i])  # pre-order add
        generateCombinations(allValues, i + 1, candidate, combinations)
        candidate.pop()


# Time complexity: O(n * 2^n)
# n - to copy the list (.copy())
# 2^n - to choose elements
if __name__ == '__main__':
    allValues = [1, 2, 3, 4, 5]
    results = []
    generateCombinations(allValues, 0, [], results)
    print(results)
    assert len(results) == pow(2, 5) - 1

    results = []
    allValues = [1, 2, 3]
    generateCombinations(allValues, 0, [], results)
    print(results)
    assert len(results) == pow(2, 3) - 1

# Execution for [1,2,3]:
# []
# |
# |-- [1]
# |   |
# |   |-- [1,2]
# |   |   |
# |   |   |-- [1,2,3]
# |   |
# |   |-- [1,3]
# |
# |-- [2]
# |   |
# |   |-- [2,3]
# |
# |-- [3]
