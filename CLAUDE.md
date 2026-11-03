# Working rules for this repo

## After finishing a task, raise a pull request

When a task is finished and the changes are committed and pushed:

1. Check whether a PR is already open for the current branch. If one is open, the new commits appear in it, so only update its description if needed.
2. If the branch has no open PR (none exists, or the last one is merged), open a new PR from this branch into `master`.
3. Do not merge PRs. Merging is the user's decision.
4. The PR description has: a summary of the change, the verification status (say clearly what was and was not run, for example "not compiled here"), and any dependency on another PR.
5. Do not ask before opening the PR. Do ask if the target branch is unclear.

Follow the attribution footer and template rules from the session instructions.
