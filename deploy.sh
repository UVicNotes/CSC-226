#!/bin/bash
set -e # exit with nonzero exit code if anything fails

LAST_COMMIT="$(git rev-parse --short HEAD)"

# clone the old gh pages
git clone "https://${GH_TOKEN}@${GH_REF}" site/
cd site/
git checkout gh-pages
cd ../

# build the docs 
mkdocs build --clean

cd site/

# inside this git repo we'll pretend to be a new user
git config user.name "Travis CI"
git config user.email "travis.ci.build@gmail.com"

# commit any new changes
git add .
git commit -m "Travis CI - Deployed ${LAST_COMMIT} with MkDocs"

# push to the repo
git push --quiet "https://${GH_TOKEN}@${GH_REF}"
