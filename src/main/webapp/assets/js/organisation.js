function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/organisation/" + id, {
        method: "DELETE"
    });
    document.location.replace("/organisation.do")
}