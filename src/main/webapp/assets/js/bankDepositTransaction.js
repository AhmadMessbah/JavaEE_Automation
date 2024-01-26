function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/bankDepositTransaction/" + id, {
        method: "DELETE"
    });
    document.location.replace("/bankDepositTransaction.do")
}