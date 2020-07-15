const addExButton = document.getElementById('add-ex-link')
const indexSection = document.querySelector('.index-section')
const AddEx = document.querySelector('.add-ex-sect')
const showButton = document.getElementById('showlist')
const buildButton = document.getElementById('buildButton')
const buildSection = document.querySelector('.build-work-section')

const navigation  = document.getElementById('nav')

AddEx.style.display = 'none';

// indexSection.style.display = 'none'
// AddEx.style.display = 'none'
// buildSection.style.display = 'none'

function showList() {
    
    showButton.addEventListener('click', ()=> {
        indexSection.style.display = 'flex'
        // AddEx.classList.add('sec-none')
        // buildSection.classList.add('sec-none')
        location.href = '#index'
    })
}

function showAdd() {
    
    addExButton.addEventListener('click', ()=> {
        AddEx.style.display = 'block'
        // indexSection.classList.add('sec-none')
        // buildSection.classList.add('sec-none')
        location.href = '#add-ex'
    })
}


function showWork() {
    
    buildButton.addEventListener('click', ()=> {
        buildSection.style.display = 'flex'
        // indexSection.classList.add('sec-none')
        // AddEx.classList.add('sec-none')
        location.href = '#build-work'
    })
}
