function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/reference/" + id, {
        method: "DELETE"
    });
    document.location.replace("/reference.do")
}