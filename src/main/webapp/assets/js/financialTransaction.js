function showEditFinancialTransaction(id) {
    document.location.replace("/financialTransactionEdit.do?id=" + id);
}

async function removeFinancialTransaction(id) {
    if (confirm("آیا از حذف تراکنش " + id + " اطمینان دارید؟")) {
        const response = await fetch("/api/financialTransaction/" + id, {
            method: "DELETE"
        });
        if (response.ok) {
            alert("تراکنش با موفقیت حذف شد");
            document.location.replace("/financialTransactionBox.do");
        } else {
            alert("حذف تراکنش با خطا مواجه شد");
        }
    }
}

async function editFinancialTransaction(event) {
    event.preventDefault();
    const financialTransactionEditForm = document.getElementById("financialTransactionEditForm");
    const formData = new FormData(financialTransactionEditForm);
    const id = formData.get("id");

    const financialTransactionData = {
        id: formData.get("id"),
        user: formData.get("user"),
        referringDepartment: formData.get("referringDepartment"),
        paymentType: formData.get("paymentType"),
        trackingCode: formData.get("trackingCode"),
        transactionType: formData.get("transactionType"),
        faDate: formData.get("faDate"),
        bankAmount: formData.get("bankAmount"),
        bank: formData.get("bank"),
        cashAmount: formData.get("cashAmount"),
        cashDesk: formData.get("cashDesk"),
        checkPayment: formData.get("checkPayment")
    };

    console.log('FinancialTransaction Data:', financialTransactionData); // برای دیباگ

    const response = await fetch("/api/financialTransaction/edit", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(financialTransactionData)
    });

    if (response.ok) {
        alert("تغییرات با موفقیت ثبت شد");
        document.location.replace("/financialTransactionDisplay.do?id=" + id);
    } else {
        console.error("Failed to update the financialTransaction");
        alert("خطایی رخ داد. لطفاً دوباره امتحان کنید.");
    }
}

function selectFinancialTransaction(id) {
    document.location.replace("/financialTransactionDisplay.do?id=" + id);
}