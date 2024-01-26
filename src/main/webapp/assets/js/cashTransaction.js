function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/cashTransaction/" + id, {
        method: "DELETE"
    });
    document.location.replace("/cashTransaction.do")
}