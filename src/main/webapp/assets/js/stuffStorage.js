function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/stuffStorage/" + id, {
        method: "DELETE"
    });
    document.location.replace("/stuffStorage.do")
}