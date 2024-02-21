async function findById(id) {
    alert(id);
    const response  = await fetch("/api/stuff/" + id, {
        method:"GET"
    });
    document.location.replace("/stuff.do")
}

// async function edit(id) {
//     alert(id);
//     const response  = await fetch("/api/stuff/" + id, {
//         method:"PUT"
//     });
//     document.location.replace("/stuff.do")
// }

async function remove(id) {
    alert(id);
    const response = await fetch("/api/stuff/" + id, {
        method: "DELETE"
    });
    document.location.replace("/stuff.do")
}
async function edit(id, name, brand, price, model, status){
    alert(id);
    const stuff = {"id": id, "name": name, "brand": brand, "price": price, "model": model, "status": status};
    const response = await fetch("/api/stuff/" + stuff, {
        method: "PUT"
    });
    document.location.replace("/stuff.do")
}