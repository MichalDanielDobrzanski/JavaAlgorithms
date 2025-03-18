def mult(x: int, y: int):
    if x == 0 or y == 0:
        return 0
    elif x % 2 == 0:
        return mult(x // 2, y + y)
    else:
        return y + mult(x // 2, y + y)


def multIter(x: int, y: int):
    if y == 0:
        return 0
    prod = 0
    while x > 0:
        if x % 2 != 0:
            prod = prod + y
        x //= 2
        y += y
    return prod


if __name__ == '__main__':
    print(mult(3, 7))

    print(mult(123, 456))  # 56088

    print(multIter(3, 7))

    print(multIter(123, 456))  # 56088
