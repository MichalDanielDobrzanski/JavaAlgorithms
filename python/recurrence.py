def sumrectd(arr, idx, acc):
    if idx == len(arr):
        return acc
    return sumrectd(arr, idx + 1, acc + arr[idx])


# In the top-down version, I start with index 0 and an accumulator acc = 0.
# As I move forward through the array, I pass the accumulated sum down the call stack.
# The base case is when the index reaches the array's length —
# at which point I return the accumulated value.
# This is tail-recursive, and if the language supports tail call optimisation,
# it could be optimised into iteration under the hood
def sumTopDown(arr):
    return sumrectd(arr, 0, 0)


def sumrecbu(arr, idx):
    if idx == len(arr) - 1:
        return arr[idx]
    return arr[idx] + sumrecbu(arr, idx + 1)


# In the bottom-up version, I recurse all the way to the end of the array first.
# Only when the recursive calls start returning do I begin combining values.
# At each step, I take the current element and add it to the result of summing the rest of the array.
# This is not tail-recursive because the addition happens after the recursive call,
# and it builds the result on the way back up the stack.
def sumBottomUp(arr):
    return sumrecbu(arr, 0)


if __name__ == '__main__':
    arr = [1, 2, 3, 4, 5]
    print("top down: " + str(sumTopDown(arr)))
    print("bottom up: " + str(sumBottomUp(arr)))
