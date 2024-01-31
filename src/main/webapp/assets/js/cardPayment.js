function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/cardPayment/" + id, {
        method: "DELETE"
    });
    document.location.replace("/cardPayment.do")
}