function edit(id) {
    alert(id);
}

async function remove(id) {
    alert(id);
    const response = await fetch("/api/user/" + id, {
        method: "DELETE"
    });
    document.location.replace("/user.do")
}