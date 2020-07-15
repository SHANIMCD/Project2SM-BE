

(function () {
    const catSelect = document.getElementById('catSelect')
    catSelect.addEventListener('click', () => {
        catSelect.options[0].disabled = true
    })
    document.getElementById('submitButton').addEventListener('click', (e) => {
        e.preventDefault();
        let info = {}
        document.querySelectorAll('#ex-form > input, #ex-form > select, #ex-form > div > input').forEach(x => info[x.name] = x.value);
        info.id = undefined;
        info.workout = {
            id: document.getElementById('addWorkoutID').value
        }
        axios.post('/create', info)
        .then(console.log(info))
            .catch(err => console.log(err))
        location.reload();
    })
})();


(function () {
    document.getElementById('createWO').addEventListener('click', (e) => {
        e.preventDefault();
        let info = {};
            document.querySelectorAll('#cWoForm > input').forEach(x => info[x.name] = x.value);
            axios.post('/wo/create', info)
            .then(res => location.reload())
            .catch(err => console.log(err))
            
        
    })
})();


