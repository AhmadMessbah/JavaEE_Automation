function edit(username) {
    alert(username);
}

async function remove(username) {
    alert(username);
    const response = await fetch("/api/user/" + username, {
        method: "DELETE"
    });
    document.location.replace("/user.do")
}