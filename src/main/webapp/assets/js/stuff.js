function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/stuff/" + id, {
        method: "DELETE"
    });
    document.location.replace("/stuff.do")
}