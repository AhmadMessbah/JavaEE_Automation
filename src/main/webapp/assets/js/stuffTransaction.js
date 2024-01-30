function edit(id) {
    alert(id);
}

function remove(id) {
    fetch("/api/stuffTransaction/" + id, {
        method: "DELETE"
    }).then(response => {
        JSON.parse(response)
    })
        .then(data => {
            alert(data);
        })
}