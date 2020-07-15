let updateExForm = document.getElementById('updateEx')
updateExForm.style.display ='none';


(function () {
    axios.get('/read')
        .then(res => {
            res.data.forEach(ex => {
                const exCon = document.getElementById('ex-index')
                const exDiv = document.createElement('div');
                exDiv.className = 'eachex'
                exCon.appendChild(exDiv);

                const id = document.createElement('p')
                id.innerText = ex.e_id
                exDiv.appendChild(id)
                id.style.display = 'none'

                const name = document.createElement('h3');
                name.innerText = ex.name;
                exDiv.appendChild(name);

                exDiv.addEventListener('click', () => {
                    getOneEx();
                })
                const category = document.createElement('p');
                category.innerText = ex.category;
                exDiv.appendChild(category);

                function getOneEx() {

                    axios.get('/read/' + ex.e_id)
                        .then(({ data: ex }) => {

                            exIndex.innerHTML = '';
                            const exDisplay = document.createElement('div')
                            exIndex.appendChild(exDisplay)

                            const name = document.createElement('h3')
                            name.innerText = ex.name
                            exIndex.appendChild(name)

                            const category = document.createElement('h4')
                            category.innerText = ex.category
                            exIndex.appendChild(category)

                            const image = document.createElement('img')
                            image.src = ex.imageMain;
                            image.className = 'ex-image'
                            exIndex.appendChild(image);

                            const deleteButton = document.createElement('button')
                            deleteButton.innerText = 'DELETE'
                            exIndex.appendChild(deleteButton)

                            deleteButton.addEventListener('click', () => {
                                axios.delete('/delete/' + ex.e_id)
                                location.reload();
                            })

                            const updateButton = document.createElement('button')
                            updateButton.innerText = 'UPDATE'
                            exIndex.appendChild(updateButton)

                
                            updateButton.addEventListener('click', () => {                          
                                updateExForm.style.display ='flex';
                                updateButton.style.display = 'none'
                                deleteButton.style.display = 'none'
                                exCon.style.display = 'none' 
                                document.getElementById('updateName').value = ex.name;
                                document.getElementById('updateImage').value = ex.imageMain;
                                document.getElementById('updateCatSelect').value = ex.category;
                                                                
                                console.log(ex.workout);
                                const catSelectUpdate = document.getElementById('updateCatSelect')
                                catSelectUpdate.addEventListener('click', () => {
                                    catSelectUpdate.options[0].disabled = true
                                })
                                document.getElementById('updateButton').addEventListener('click', (e) => {
                                    e.preventDefault();
                                    let info = {};
                                    document.querySelectorAll('#update-ex-form > input, #update-ex-form > select').forEach(x => info[x.name] = x.value);
                                    info.id = undefined;
                                    info.workout = {
                                        id: document.getElementById('updateWorkoutID').value
                                    }
                                    axios.put('/update/' + ex.e_id, info)
                                        .then(res => console.log(res))
                                        .catch(err => console.error(err));
                                    location.reload();
                                });
                            })

                        })
                }
            })
        })
        .catch(err => console.log(err))
})();
