# SpecificBreakPlace
WorldGuard expansion to allow breaking and placing specific blocks.

# How to setup?

1. Drag the jar file into the .plugins folder.

2. Use the command /rg flags and look for the flags "custom-block-break" and "custom-block-place". Then, add the list of blocks that you want players to be able to place or break.
Note: This plugin only works in regions where the main WorldGuard flag for block-break and block-place is set to DENY.

3. After doing these steps, you'll be able to break blocks, but you might encounter a few issues, such as:
- A message like "you can't break this" will appear in the chat. To disable this message, use the command ''/rg flag -w "worldname" -h 6 regionname exit-deny-message &f''
- When you break the block, even though it breaks and is processed by other plugins, particles from WorldGuard will still appear. You can disable these in the config.yml by setting ''use-particle-effects: false''

If you followed these steps, you've successfully "installed" the plugin on your server. Good luck, buddy!
