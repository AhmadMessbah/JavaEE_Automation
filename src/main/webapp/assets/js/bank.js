function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/bank/" + id, {
        method: "DELETE"
    });
    document.location.replace("/bank.do")
}