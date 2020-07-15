const buildWorkoutSec = document.querySelector('.build-work-section')
const updateWoForm = document.getElementById('updateWoForm')
updateWoForm.style.display = 'none'

let buildCont = document.createElement('div')
buildWorkoutSec.appendChild(buildCont)

let buildTitle = document.createElement('h3')
buildCont.appendChild(buildTitle)
// buildTitle.innerText = 'Workout';
buildTitle.className = 'build-title';



(function () {
    axios.get('/wo/read')
    .then(res => {
        res.data.forEach(wo => {
            const woCon = document.createElement('div')
            woCon.className = 'woContainer'
            buildWorkoutSec.appendChild(woCon)

            const id = document.createElement('p')
            id.innerText = wo.id
            woCon.appendChild(id)

            const title = document.createElement('h3')
            title.innerText = wo.title
            woCon.appendChild(title)


            title.addEventListener('click', function () {
                getOneWorkout();
            })

            wo.exercises.forEach(ex => {
                const exName = document.createElement('p')
                exName.innerText = ex.name
                woCon.appendChild(exName)
            })

            function getOneWorkout() {
                axios.get('/wo/read/' + wo.id)
                .then(res => {
                    const wo = res.data;
                    woCon.innerHTML = '';
                    const woDisplay = document.createElement('div')
                    woCon.appendChild(woDisplay)
    
                    const title = document.createElement('h3')
                    title.innerText = wo.title
                    woCon.appendChild(title)

                    const deleteButton = document.createElement('button')
                            deleteButton.innerText = 'DELETE'
                            woCon.appendChild(deleteButton)

                    deleteButton.addEventListener('click', function () {
                        axios.delete('/wo/delete/' + wo.id)
                        location.reload();
                    })
                    
                    const updateButton = document.createElement('button');
                            updateButton.innerText = 'UPDATE';
                            woCon.appendChild(updateButton);

                    const addExButton = document.createElement('button');
                            addExButton.innerText = 'ADD EXERCISE';
                            woCon.appendChild(addExButton);

                    addExButton.addEventListener('click', function () {
                        document.querySelector('.add-ex-sect').style.display = 'block';
                        addExButton.style.display = 'none';
                        document.getElementById('addWorkoutID').value = wo.id;
                                                
                    })

                updateButton.addEventListener('click', function () {
                    document.getElementById('cWoForm').style.display = 'none'
                    updateWoForm.style.display = 'block';
                    deleteButton.style.display = 'none';
                    updateButton.style.display = 'none';

                    document.getElementById('updateTitle').value = wo.title;
                    document.getElementById('updateWoBtn').addEventListener('click', (e) => {
                        e.preventDefault();
                        let info = {};
                        document.querySelectorAll('#updateWoForm > input').forEach(x => info[x.name] = x.value);
                        axios.put('http://localhost:8081/wo/update/' + wo.id, info)
                        .then(res => console.log(res))
                        .catch(err => console.log(err));
                        location.reload();
                    })
                })

                })
            }
            
        })
    })
})();