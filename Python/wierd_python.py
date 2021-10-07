class Foo:
    def __init__(self):
        self.x = 0

    def __setattr__(self, key, value):
        if int(value) < 0:
            self.__dict__[key] = -1
        elif len(str(value)) < 2:
            self.__dict__[key] = int(value)
        else:
            value = str(value)
            self.__dict__[key] = int(value[len(value) - 2:len(value)])


p = Foo()
print(p.x)
p.x = 125
print(p.x)
p.x = 15874
print(p.x)
p.x = 8
print(p.x)
p.x = 13
print(p.x)
p.x = -15698
print(p.x)
p.x = 1234
print(p.x)
print(p.x == 34)
print(type(p.x))
