function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/checkPayment/" + id, {
        method: "DELETE"
    });
    document.location.replace("/checkPayment.do")
}