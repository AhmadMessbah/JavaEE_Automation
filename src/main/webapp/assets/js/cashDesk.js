function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/cashDesk/" + id, {
        method: "DELETE"
    });
    document.location.replace("/cashDesk.do")
}