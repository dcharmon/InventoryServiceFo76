/**
 * Enables or disables star effect dropdowns in a chain based on whether
 * the previous tier has a value selected.
 *
 * Applies to both standard armor and power armor piece forms.
 */
function updateStarDropdowns() {
    var star1 = document.getElementById('star1EffectId');
    var star2 = document.getElementById('star2EffectId');
    var star3 = document.getElementById('star3EffectId');
    var star4 = document.getElementById('star4EffectId');

    var has1 = star1.value !== '';
    var has2 = has1 && star2.value !== '';
    var has3 = has2 && star3.value !== '';

    // Enable/disable based on previous tier
    star2.disabled = !has1;
    star3.disabled = !has2;
    star4.disabled = !has3;

    // Reset lower tiers if a higher tier is cleared
    if (!has1) { star2.value = ''; star3.value = ''; star4.value = ''; }
    if (!has2) { star3.value = ''; star4.value = ''; }
    if (!has3) { star4.value = ''; }
}

// Attach listeners and run once on load to set initial state
document.getElementById('star1EffectId').addEventListener('change', updateStarDropdowns);
document.getElementById('star2EffectId').addEventListener('change', updateStarDropdowns);
document.getElementById('star3EffectId').addEventListener('change', updateStarDropdowns);
updateStarDropdowns();