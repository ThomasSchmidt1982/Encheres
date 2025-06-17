document.addEventListener('DOMContentLoaded', function() {
    // Récupération des éléments
    const form = document.querySelector('.form-container');
    const motDePasse = document.getElementById('motDePasse');
    const confirmation = document.getElementById('confirmation');

    if (form && motDePasse && confirmation) {
        form.addEventListener('submit', function(e) {
            if (motDePasse.value !== confirmation.value) {
                e.preventDefault();
                alert('Les mots de passe ne correspondent pas');
                confirmation.value = '';
                confirmation.focus();
            }
        });

        // Vérification en temps réel
        confirmation.addEventListener('input', function() {
            if (this.value === motDePasse.value) {
                this.style.borderColor = 'green';
            } else {
                this.style.borderColor = 'red';
            }
        });
    } else {
        console.error('Un ou plusieurs éléments du formulaire sont manquants');
    }
});