# Security Policy

## Scope

Java Atlas is a static teaching site and a collection of example programs. It has no server, accounts, database, or analytics. The most relevant risks are:

- a script-injection flaw in a lab or the site;
- a malicious link, image, or embedded resource;
- a dependency or build-chain issue;
- sensitive information accidentally committed to the repository.

## Supported versions

The `master` branch and the latest release receive security fixes. Older snapshots are not maintained.

| Version | Supported |
| :--- | :---: |
| `master` / latest `1.x` | Yes |
| Older snapshots | No |

## Reporting a vulnerability

**Do not open a public issue for a security problem.**

Use GitHub's private vulnerability reporting:

**[Report a vulnerability](https://github.com/M0-AR/YL/security/advisories/new)**

Please include:

- the affected page, file, or command;
- a short reproduction, including the browser or JDK version;
- the impact you believe it has;
- any suggested fix, if you have one.

You can expect an initial response on a best-effort basis, usually within a few days. If the report is valid, a fix and a release note will be prepared, and you will be credited unless you prefer otherwise.

## Safe harbor

Good-faith research that follows this policy, avoids privacy violations, avoids destructive testing, and gives the maintainer reasonable time to respond will not be pursued or reported as abuse.

## Out of scope

- Issues that require a compromised browser, operating system, or network.
- Missing security headers on a static hosting provider that is outside this repository's control.
- Denial-of-service reports based on extremely large local files.
- Reports about the textbook, its publisher, or third-party sites linked from this project.

## Dependency hygiene

The companion project uses a small, explicit dependency set: JUnit 5 for tests and standard Maven plugins. The site loads fonts and Three.js from public CDNs. If a dependency or CDN becomes a security concern, open a private report using the link above.
