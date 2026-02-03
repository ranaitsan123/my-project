# Documentation index

This folder contains the project’s technical and process documentation — use it for onboarding, design reviews, deployment instructions, and troubleshooting.

## Key documents
- `ARCHITECTURE.md` — system overview and diagrams
- `API_CONTRACT.md` — service API definitions and example payloads
- `DEVELOPMENT.md` — how to run, test and contribute locally
- `DEPLOYMENT.md` — deployment steps and environment variables
- `DATABASE.md` — schema and initialization scripts
- `TESTING.md` — testing strategy and integration test guidance
- `SECURITY.md`, `TROUBLESHOOTING.md`, `CHANGELOG.md`, `SERVICES.md`

## Quick onboarding checklist
1. Read `ARCHITECTURE.md` to understand service boundaries.
2. Start the local stack: `docker compose up --build`.
3. Run smoke tests: `./test-auth.sh`.
4. Run unit tests for the service you will work on: `./<service>/mvnw test`.
5. Read `DEVELOPMENT.md` for local dev workflow and debugging tips.

## How to contribute to docs
- Make changes on a topic branch and open a PR targeting `main`.
- Link the PR to an issue and include a short summary and screenshots/diagrams when relevant.
- For API changes, update `API_CONTRACT.md` and add/increment integration tests.

## Where to find help
- Code questions: check `DEVELOPMENT.md` and service-level README/comments.
- Runtime issues: `TROUBLESHOOTING.md` and `docker compose logs -f`.

---

If you'd like, I can convert any of these markdown files into a docs site (MkDocs) and add a sidebar navigation.