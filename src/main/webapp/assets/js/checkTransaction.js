function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/checkTransaction/" + id, {
        method: "DELETE"
    });
    document.location.replace("/checkTransaction.do")
}