
class Atom():
    def __init__(self, content=None):
        self.content = content
        self.type = Atom.getType(content)
        
    @classmethod
    def getType(cls, content):
        if(content == None): return 0
        elif(isinstance(content, str)): return 1
        else: return 2
        
    def toString(self):
        if(self.type == 0): return ''
        elif(self.type == 1): return self.content
        else: return self.content.toString()            

# Triplets can take 3 forms:
# 1.) Predicate   Object      NULL   
# 2.) Subject     Predicate   Object
# 3.) Subject     Predicate   Pointer

class Triplet():
    ID = 0
    def __init__(self, a, b, c=None):
        self.a = a; self.b = b; self.c = c
        self.type = Triplet.getType(c)
        self.ID = Triplet.ID
        Triplet.ID = Triplet.ID+1
    
    @classmethod
    def getType(cls, c): return(c.type)
    
    def toString(self):
        return(self.a.toString() + " " + 
               self.b.toString() + " " + self.c.toString())
        
        
        
t0 = Triplet(Atom('GOTO'), Atom('THEPLAYOFFS'), Atom())
t1 = Triplet(Atom('OU'), Atom('SHOULD'), Atom(t0))
t2 = Triplet(Atom('I'), Atom('THINK'), Atom(t1))

print(str(t0.type) + ' ' + str(t1.type) + ' ' + str(t2.type))
print(t2.toString())

