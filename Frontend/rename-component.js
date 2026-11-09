const fs = require('fs');
const path = require('path');

const oldName = 'courses';      // ancien nom du composant (dossier + nom fichier)
const newName = 'etablissement';  // nouveau nom du composant

const componentDir = path.join('src', 'app', 'pages', oldName);
const newDir = path.join('src', 'app', 'pages', newName);

if (!fs.existsSync(componentDir)) {
  console.error(`Le dossier du composant ${oldName} n'existe pas.`);
  process.exit(1);
}

if (fs.existsSync(newDir)) {
  console.error(`Un dossier nommé ${newName} existe déjà.`);
  process.exit(1);
}

// 1. Renommer le dossier
fs.renameSync(componentDir, newDir);

// 2. Renommer et modifier les fichiers à l'intérieur
fs.readdirSync(newDir).forEach(file => {
  const oldFilePath = path.join(newDir, file);
  const newFileName = file.replaceAll(oldName, newName);
  const newFilePath = path.join(newDir, newFileName);

  // Lire contenu et remplacer class, selector...
  let content = fs.readFileSync(oldFilePath, 'utf-8');
  const classOld = capitalize(oldName) + 'Component';
  const classNew = capitalize(newName) + 'Component';

  content = content
    .replaceAll(oldName, newName)
    .replaceAll(classOld, classNew)
    .replaceAll(`app-${oldName}`, `app-${newName}`);

  fs.writeFileSync(oldFilePath, content, 'utf-8');

  // Renommer le fichier
  fs.renameSync(oldFilePath, newFilePath);
});

console.log(`✅ Composant ${oldName} renommé en ${newName} avec succès.`);

function capitalize(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}
