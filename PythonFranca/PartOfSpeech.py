
class PartOfSpeech():
    def __init__(self, name):
        self.name = name
        self.abstract = False
        
#class Noun(PartOfSpeech):   
class Verb(PartOfSpeech):
    def __init__(self, partofspeech):
        self = partofspeech
    
    
    
    
class Triplet():
    #subject and object are nouns
    #verb is a verb
    def __init__(self, tsubject, tverb, tobject):
        self.subjecct = tsubject
        self.verb = tverb
        self.object = tobject
        
