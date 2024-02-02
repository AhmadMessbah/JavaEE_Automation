function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/letter/" + id, {
        method: "DELETE"
    });
    document.location.replace("/letter.do")
}