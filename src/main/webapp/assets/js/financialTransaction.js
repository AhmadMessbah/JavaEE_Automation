function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/financialTransaction/" + id, {
        method: "DELETE"
    });
    document.location.replace("/financialTransaction.do")
}