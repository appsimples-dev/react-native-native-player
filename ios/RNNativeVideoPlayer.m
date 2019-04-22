
#import "RNNativeVideoPlayer.h"

@implementation NativeVideoPlayer

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(openPlayer:(NSString *)url)
{
    NSURL *videoURL = [NSURL URLWithString: url];
    AVPlayer *player = [AVPlayer playerWithURL:videoURL];
    // create a player view controller
    AVPlayerViewController *controller = [[AVPlayerViewController alloc] init];
    dispatch_async(dispatch_get_main_queue(), ^{
        UIViewController *rootViewController = [[[[UIApplication sharedApplication] delegate] window] rootViewController];
        [rootViewController presentViewController:controller animated:YES completion:nil];
        controller.player = player;
        [player play];
    });
}

@end

