def directoriesToCategories = [
    'ateliers-formations': 'ateliers',
    'hippy': 'ateliers',
    'peinture': 'ateliers',
    'boite-bijoux': 'cartonnage',
    'cartonnage': 'cartonnage',
    'outils-pedagogiques': 'outils-pedagogiques',
    'butai': 'outils-pedagogiques',
    'deguisement': 'jeux-jouets',
    'cadre-star-wars-papa': 'arts-plastiques',
    'collage': 'arts-plastiques',
    'gravure': 'arts-plastiques',
    'pate-a-sucre': 'patisseries',
    'mei-tei': 'sacs-enfants',
    'gigoteuse': 'sacs-enfants',
    'plaid': 'sacs-enfants',
    'poupees-animaux': 'sacs-enfants',
    'sacs': 'sacs-enfants'
    ]
    
def categories = [
    'ateliers': 'Ateliers',
    'cartonnage': 'Cartonnage',
    'outils-pedagogiques': 'Outils pédagogiques',
    'jeux-jouets': 'Jeux et jouets',
    'arts-plastiques': 'Arts plastiques',
    'sacs-enfants': 'Sacs et enfants',
    'patisseries': 'Pâtisseries'
    ]

def files = []

import groovy.io.FileType
new File('./static/img/expertise').traverse(type : FileType.FILES) { file -> 
    if (file.name =~ /[a-z]+-*[a-z]+1{0,1}.(jpg|JPG|png)/) {
        
        files << ['name': file.name.minus('.jpg').minus('.JPG').minus('png'), 'path': file.path.minus('./static'), 'category': []] 
        
        /*def directories = file.path.tokenize(File.separator)
        def directory = directories.find { directoriesToCategories[it] }
        def category = directoriesToCategories[directory]
       
        files << ['name':file.name.minus('.jpg'), 'path': file.path.minus('./static'), 'category': categories[category]]*/
    } 
}

def text = '+++\ntitle = "$name"\ncategories = [ "" ]\ntags = [ "" ]\nimg = "$path"\n+++'

def engine = new groovy.text.SimpleTemplateEngine()
def folder = new File('./content/expertise')
folder.deleteDir()
folder.mkdir()
files.each { file ->
    def content = engine.createTemplate(text).make(file)    
    new File('./content/expertise/'+file.name+'.md') << content
}
