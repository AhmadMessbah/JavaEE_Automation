async function findById(id) {
    alert(id);
    const response  = await fetch("/api/stuff/" + id, {
        method:"GET"
    });
    document.location.replace("/stuff.do")
}

async function edit(id) {
    alert(id);
    const response  = await fetch("/api/stuff/" + id, {
        method:"PUT"
    });
    document.location.replace("/stuff.do")
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/stuff/" + id, {
        method: "DELETE"
    });
    document.location.replace("/stuff.do")
}